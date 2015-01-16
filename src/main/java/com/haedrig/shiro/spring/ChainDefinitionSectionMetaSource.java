package com.haedrig.shiro.spring;

import java.util.List;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.haedrig.entity.Resource;
import com.haedrig.service.IResourceService;

public class ChainDefinitionSectionMetaSource implements FactoryBean<Section> {

	@Autowired
	private IResourceService resourceService;

	private String filterChainDefinitions;
	
	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	@Override
	public Section getObject() throws BeansException {
		Ini ini = new Ini();
		ini.load(filterChainDefinitions);
		Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
		if (CollectionUtils.isEmpty(section)) {
			section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		}
		List<Resource> resources = resourceService.getAll();
		for (Resource resource : resources) {
			section.put(resource.getUrl(), resource.getPerms());
		}
		return section;
	}

	@Override
	public Class<Section> getObjectType() {
		return Section.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
