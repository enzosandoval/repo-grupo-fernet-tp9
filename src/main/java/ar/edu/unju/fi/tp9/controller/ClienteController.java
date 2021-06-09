/**
 * 
 */
package ar.edu.unju.fi.tp9.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.unju.fi.tp9.entity.Cliente;
import ar.edu.unju.fi.tp9.entity.Beneficio;
import ar.edu.unju.fi.tp9.service.IBeneficioService;
import ar.edu.unju.fi.tp9.service.IClienteService;

/**
 * @author Team Fernet
 *
 */
@Controller
public class ClienteController {

	@Autowired
	@Qualifier("clienteServiceImp")
	private IClienteService clienteService;

	@Autowired
	@Qualifier("beneficioServiceImp")
	private IBeneficioService beneficioService;

	@Autowired
	private Cliente cliente;

	/**
	 * 
	 * @param model
	 * @return La página para el alta de nuevo Cliente
	 */
	@GetMapping("/cliente/nuevo")
	public String getClientePage(Model model) {
		cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		model.addAttribute("beneficios", beneficioService.obtenerBeneficios());
		return "nuevocliente.html";
	}

	@PostMapping("/cliente/guardar")
	public String getModelPageClientes(@ModelAttribute("cliente") @Valid Cliente cliente,
			BindingResult validationResult) {
		if (validationResult.hasErrors()) { // 🛑 Datos con errores, re-direccionar a:
			return "redirect:/cliente/nuevo";
		} else { // ✅ Datos todos correctos, hacer y re-direccionar a:

			List<Beneficio> beneficios = new ArrayList<Beneficio>();
			for (Beneficio beneficio : cliente.getBeneficios()) {

				beneficios.add(beneficioService.buscarBeneficio(beneficio.getId()));
			}
			cliente.setBeneficios(beneficios);
			clienteService.guardar(cliente);
			return "redirect:/cliente/lista";
		}
	}

	@GetMapping("/cliente/lista")
	public String getListadoPage(@RequestParam Map<String, Object> params, Model model) {
		model.addAttribute("cliente", cliente);
		int page = params.get("page") != null ? Integer.valueOf(params.get("page").toString()) - 1 : 0;
		PageRequest pageRequest = PageRequest.of(page, 5);
		Page<Cliente> pageCliente = clienteService.findAll(pageRequest);
		int totalPage = pageCliente.getTotalPages();
		if (totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}
		model.addAttribute("clientes", pageCliente.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
		return "clientes";
	}

	@GetMapping(value = "/cliente/modificar/{id}")
	public String getUpdatePage(@PathVariable(value = "id") int id, Model model) throws Exception {
		cliente = clienteService.obtenerClienteById(id);
		model.addAttribute("cliente", cliente);
		model.addAttribute("beneficios", beneficioService.obtenerBeneficios());
		return "nuevocliente";
	}

	@GetMapping(value = "/cliente/delete/{id}")
	public String delete(@PathVariable(value = "id") int id) {
		clienteService.borrar(id);
		return "redirect:/cliente/lista";
	}

}
