package com.daw.themadridnews.administrator;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.daw.themadridnews.requests.FormAdminUser;
import com.daw.themadridnews.user.User;
import com.daw.themadridnews.user.UserRepository;
import com.daw.themadridnews.user.UserView;
import com.daw.themadridnews.utils.Message;
import com.daw.themadridnews.utils.ModPagination;
import com.daw.themadridnews.utils.ModPagination.ModPageItem;


@Controller
public class AdministratorController {
	
	@Autowired
	protected UserRepository userRepository;
	
	protected static final int nItemsList = 20;
	
	
	@RequestMapping(value="/administrador", method=RequestMethod.GET)
	public ModelAndView showAdminIndex(HttpServletResponse response) {
		return new ModelAndView( new RedirectView("/administrador/usuario/lista") );
	}
	
	@RequestMapping(value="/administrador/usuario/{id}", method=RequestMethod.GET)
	public ModelAndView showFormModify(Model model, @PathVariable long id) {
		Message message;
		User user = userRepository.findOne(id);
		
		if(user == null) {
			message = new Message(1, "El usuario no existe. Por favor, seleccione uno de la lista.", "danger");
			model.addAttribute("message", message);
			return showList(model);
		}

		UserView uv = new UserView(user);
		
		model.addAttribute("fuser_name", uv.getName());
		model.addAttribute("fuser_lastname", uv.getLastname());
		model.addAttribute("fuser_alias", uv.getAlias());
		model.addAttribute("fuser_role_editor", uv.hasRole("ROLE_EDITOR"));
		model.addAttribute("fuser_role_publicist", uv.hasRole("ROLE_PUBLICIST"));
		model.addAttribute("fuser_role_admin", uv.hasRole("ROLE_ADMIN"));
		
		return new ModelAndView("admin_user_form");
	}
	
	@RequestMapping(value="/administrador/usuario/{id}", method=RequestMethod.POST)
	public ModelAndView showFormModify(Model model, FormAdminUser r, @PathVariable long id) {
		Message message;
		User user = userRepository.findOne(id);
		
		if(user == null) {
			message = new Message(1, "El usuario no existe. Por favor, seleccione uno de la lista.", "danger");
			model.addAttribute("message", message);
			return showList(model);
		}
		
		message = r.validation();
		if(message.getCode() != 0) {
			model.addAttribute("message", message);
			return showFormModify(model, id);
		}

		user.setName(r.getName());
		user.setLastname(r.getLastname());
		user.setAlias(r.getAlias());
		user.setRoles(r.getRoles());
		userRepository.save(user);
		
		return new ModelAndView( new RedirectView("/administrador/usuario/lista/guardado") );
	}
	
	@RequestMapping(value="/administrador/usuario/{id}/eliminar", method=RequestMethod.GET)
	public ModelAndView deleteAd(Model model, @PathVariable long id) {
		userRepository.delete(id);
		return new ModelAndView( new RedirectView("/administrador/usuario/lista/aliminado") );
	}
	
	@RequestMapping(value="/administrador/usuario/lista", method=RequestMethod.GET)
	public ModelAndView showList(Model model) {
		return showListAux(model, 0);
	}
	
	@RequestMapping(value="/administrador/usuario/lista/guardado", method=RequestMethod.GET)
	public ModelAndView showListPublished(Model model) {
		Message message = new Message(0, "El usuario ha sido guardado correctamente", "success");
		model.addAttribute("message", message);
		return showListAux(model, 0);
	}
	
	@RequestMapping(value="/administrador/usuario/lista/eliminado", method=RequestMethod.GET)
	public ModelAndView showListDeleted(Model model) {
		Message message = new Message(0, "El usuario ha sido eliminado correctamente", "success");
		model.addAttribute("message", message);
		return showListAux(model, 0);
	}
	
	@RequestMapping(value="/administrador/usuario/lista/{nPage}", method=RequestMethod.GET)
	public ModelAndView showList(Model model, @PathVariable int nPage) {
		return showListAux(model, nPage-1);
	}
	
	private ModelAndView showListAux(Model model, int nPage) {
		Page<User> page = userRepository.findAll( new PageRequest(nPage, nItemsList, Sort.Direction.DESC, "id") );
		
		List<User> userList = page.getContent();
		model.addAttribute("fuser_list", UserView.castList(userList) );
		
		ModPagination modPagination = new ModPagination();
		List<ModPageItem> pageList = modPagination.getModPageList(page, "/administrador/usuario/lista/");
		model.addAttribute("page_list", pageList);
		
		return new ModelAndView("admin_user_list");
	}
}
