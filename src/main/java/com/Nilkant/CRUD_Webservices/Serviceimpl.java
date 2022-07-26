package com.Nilkant.CRUD_Webservices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Serviceimpl implements Serviceinterface {

	@Autowired
	Studentrepository repository;

	public String savedata(Student stu) {
	
		repository.save(stu);
		return "Saved Data";
	}

	@Override
	public Student logincheck(Student stu) {
		String uname=stu.getUsername();
		String pass=stu.getPwd();
		Student user=repository.findAllByUsernameAndPwd(uname, pass);
		return user;
	}

	@Override
	public Iterable<Student> viewalldata() {
	   
		return repository.findAll();
	}

	@Override
	public String deleteData(Student stu) {
		System.out.println("In service delete");
		int id=stu.getId();
		System.out.println("Id for delete "+id);
		repository.deleteById(id);
		String msg="Deleted Successfully";
		System.out.println(msg);
		return msg;
	}

	@Override
	public Student getvaluebyid(int id) {
		//int sid=Integer.parseInt(id);
		
		return repository.findAllById(id);
	}

	@Override
	public Iterable<Student> updatedata(Student stu) {
		repository.save(stu);
		return repository.findAll();
	}

	
	
}
