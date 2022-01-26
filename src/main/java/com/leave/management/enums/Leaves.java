package com.leave.management.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Leaves {

    CASUAL_LEAVE("Casual Leave"),
    SICK_LEAVE("Sick Leave"),
    PAID_LEAVE("Paid Leave");

    public String leaveType;

    Leaves(String leaveType) {
        this.leaveType = leaveType;
    }

    public static Optional<Leaves> getLeave(String leaveType) {

        return Arrays.stream(Leaves.values()).filter(leaves -> leaves.leaveType.equalsIgnoreCase(leaveType)).findFirst();
    }

}
