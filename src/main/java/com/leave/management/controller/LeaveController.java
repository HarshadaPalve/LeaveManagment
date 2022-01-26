package com.leave.management.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.leave.management.enums.Leaves;
import com.leave.management.service.LeaveService;
import java.util.Calendar;
import java.util.Optional;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @GetMapping(value = "/apply")
    public String applyLeaveInSystem(@RequestParam("employeeID") Integer employeeID,
                                     @RequestParam("leaveType") String leaveType,
                                     @RequestParam("leaveCount") Integer leaveCount) {

        if (leaveService.isEmployeeNotAvailable(employeeID))
            return "Employee is not present in the System";

        Optional<Leaves> leavesOptional = Leaves.getLeave(leaveType);
        if (leavesOptional.isPresent()) {
            return leaveService.applyLeave(leavesOptional.get(), leaveCount, employeeID);
        } else {
            return "Leave Type is not appropriate";
        }
    }

    @GetMapping("/lapse")
    public String lapseLeaves() {

        Calendar calendar = Calendar.getInstance();
        return leaveService.lapseYearEndLeaves(calendar);
    }
}
