package com.haedrig.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haedrig.dao.IResourceDAO;
import com.haedrig.entity.Resource;
import com.haedrig.service.IResourceService;

@Service("resourceService")
public class ResourceServiceImpl implements IResourceService {
	
	@Autowired
	private IResourceDAO resourceDAO;

	public List<Resource> getAll() {
		return resourceDAO.getAll();
	}

}
