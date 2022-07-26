package com.Nilkant.CRUD_Webservices;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Homecontroller {

    @Autowired
	private Serviceimpl service;

	@RequestMapping(value="/register", method=RequestMethod.POST, consumes="application/json")
	public List<Student> registration(@RequestBody Student stu) {
		//System.out.println("In register control");
		System.out.println(stu.getUsername());
		String msg = service.savedata(stu);
		Iterable<Student> slist = service.viewalldata();
		
		return (List<Student>) slist;

	}

	@RequestMapping(value="/log/{uname} {pwd}",method=RequestMethod.POST, consumes="application/json")
	public List<Student> logincheck(@PathVariable String uname,@PathVariable String pwd) {
		//Student loguser = service.logincheck(loginstudent);

		if (uname.equals("admin") && pwd.equals("admin")) {
			Iterable<Student> slist = service.viewalldata();
			
			return (List<Student>) slist;
		}
		 if (uname == null && pwd==null) { String msg1 = "Incorrect credentials"; return null; }
		
		return null ;
	}

	@RequestMapping(value="/delete/{id}")
	public List<Student> deleteData(@PathVariable int id) {
		Student sdel=service.getvaluebyid(id);
		service.deleteData(sdel);
		Iterable<Student> dellist = service.viewalldata();
		return (List<Student>) dellist;
	}

	@RequestMapping(value="/updatedata",method=RequestMethod.POST, consumes="application/json")
	public List<Student> viewupdatedata(@RequestBody Student stu) {
		Iterable<Student> stulist = service.updatedata(stu);
		return (List<Student>) stulist;
	}
}
