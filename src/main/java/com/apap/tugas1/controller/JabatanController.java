package com.apap.tugas1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;

@Controller
public class JabatanController {
	@Autowired
	private JabatanService jabatanService;
	
	@Autowired
	private InstansiService instansiService;
	
	@RequestMapping("/")
	private String home(Model model) {
		List<JabatanModel> listAllJabatan = jabatanService.getAllJabatan();
		List<InstansiModel> listAllInstansi = instansiService.getAllInstansi();
		
		model.addAttribute("listAllInstansi", listAllInstansi);
		model.addAttribute("listAllJabatan", listAllJabatan);
		return "home";
	}
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.GET)
	private String add(Model model) {
		JabatanModel jabatan = new JabatanModel();
		
		model.addAttribute("jabatan", jabatan);
		return "tambah-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
	private String addJabatan(@ModelAttribute JabatanModel jabatan, Model model) {
		jabatanService.addJabatan(jabatan);
		
		model.addAttribute("jabatan", jabatan);
		return "tambah";
	}
	
	@RequestMapping(value = "/jabatan/view", method = RequestMethod.GET)
	private String viewJabatan(@RequestParam("id") long id, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanDetailById(id);
		
		model.addAttribute("jabatan", jabatan);
		return "view-jabatan";
	} 
	
	@RequestMapping(value = "/jabatan/ubah/{id}", method = RequestMethod.GET)
	private String updateJabatan(@PathVariable(value="id") long id, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanDetailById(id);

		model.addAttribute("jabatan", jabatan);
		return "ubah-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/ubah/{id}", method = RequestMethod.POST)
	private String updateJabatan(@PathVariable(value="id") long id, @ModelAttribute JabatanModel jabatan, Model model) {
		jabatanService.updateJabatan(id, jabatan);
		
		model.addAttribute("jabatan", jabatan);
		return "ubah";
	}
	
	@RequestMapping(value = "/jabatan/hapus/{id}", method = RequestMethod.POST)
	private String deleteJabatan(@PathVariable(value="id") long id, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanDetailById(id);
		if(jabatan.getPegawai().isEmpty()) {
			jabatanService.deleteJabatan(id);
			return "delete";
		}return "delete-failed";
	}
	
	@RequestMapping(value = "/jabatan/viewall", method = RequestMethod.GET)
	private String viewAllJabatan(Model model) {
		List<JabatanModel> listAllJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", listAllJabatan);
		return "viewall-jabatan";
	}
	
}
