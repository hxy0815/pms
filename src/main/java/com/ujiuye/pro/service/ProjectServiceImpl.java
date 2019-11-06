package com.ujiuye.pro.service;

import com.ujiuye.cust.bean.Customer;
import com.ujiuye.cust.mapper.CustomerMapper;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.ProjectExample;
import com.ujiuye.pro.mapper.ProjectMapper;
import com.ujiuye.sys.bean.Employee;
import com.ujiuye.sys.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private CustomerMapper customerMapper;

    public void saveInfo(Project project) {
        projectMapper.insert(project);
    }

    public List<Project> getProjectList() {
        ProjectExample example = new ProjectExample();
        List<Project> projects = projectMapper.selectByExample(example);
        for (Project project:projects) {
            Employee employee = employeeMapper.selectByPrimaryKey(project.getEmpFk());
            Customer customer = customerMapper.selectByPrimaryKey(project.getComname());
            project.setEname(employee.getEname());
            project.setCname(customer.getComname());
        }
        return projects;
    }

    public Project getProjectDetail(Integer pid) {
        Project project = projectMapper.selectByPrimaryKey(pid);
        Employee employee = employeeMapper.selectByPrimaryKey(project.getEmpFk());
        project.setEname(employee.getEname());
        return project;
    }

    public void updateProject(Project project) {
        projectMapper.updateByPrimaryKeySelective(project);
    }

    public boolean batchDelete(Integer[] pids) {
        List<Integer> pidList = Arrays.asList(pids);
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andPidIn(pidList);
        int i = projectMapper.deleteByExample(example);
        return pids.length == i;
    }

    public List<Project> search(Integer cid, String keyword, Integer orderby) {
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        if(cid == 0){
            criteria.andPnameLike("%"+keyword+"%");
            ProjectExample.Criteria criteria1 = example.createCriteria();
            criteria1.andComperLike("%"+keyword+"%");
            example.or(criteria1);
        }else if(cid == 1){
            criteria.andPnameLike("%"+keyword+"%");
        }else{
            criteria.andComperLike("%"+keyword+"%");
        }
        if(orderby ==1){
            example.setOrderByClause("pid desc");
        }
        List<Project> projects = projectMapper.selectByExample(example);
        for (Project project:projects) {
            Employee employee = employeeMapper.selectByPrimaryKey(project.getEmpFk());
            Customer customer = customerMapper.selectByPrimaryKey(project.getComname());
            project.setEname(employee.getEname());
            project.setCname(customer.getComname());
        }
        return projects;
    }

    public List<Project> getProjectWithAnalyseJsonList() {
        return projectMapper.getProjectWithAnalyseJsonList();
    }


}
