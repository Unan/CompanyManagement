package com.altarix.task.schedule;

import com.altarix.task.dao.DepartmentDAO;
import com.altarix.task.dao.ScheduleDAO;
import com.altarix.task.model.Department;
import com.altarix.task.model.schedule.DepartmentWageInfo;
import com.altarix.task.service.departmentService.DepartmentService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final ScheduleDAO scheduleDAO;
    private final DepartmentDAO departmentDAO;
    private final DepartmentService departmentService;

    public ScheduledTasks(ScheduleDAO scheduleDAO, DepartmentDAO departmentDAO, DepartmentService departmentService) {
        this.scheduleDAO = scheduleDAO;
        this.departmentDAO = departmentDAO;
        this.departmentService = departmentService;
    }

    @Scheduled(fixedRate = 300000)
    public void reportCurrentTime() {

        for (Department department: departmentDAO.all()) {
            scheduleDAO.update(new DepartmentWageInfo(department.getId(), department.getName(), departmentService.employeesWageAmount(department)));
        }
    }
}