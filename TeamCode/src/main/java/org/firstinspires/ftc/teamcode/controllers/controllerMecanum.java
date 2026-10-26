package org.firstinspires.ftc.teamcode.controllers;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class controllerMecanum{

    private DcMotor fatadreapta, fatastanga, spatedreapta, spatestanga;

    public controllerMecanum(HardwareMap hardwareMap){

        spatedreapta = hardwareMap.get(DcMotor.class, "fatastanga");
        fatadreapta = hardwareMap.get(DcMotor.class, "spatestanga");
        spatestanga = hardwareMap.get(DcMotor.class, "fatadreapta");
        fatastanga = hardwareMap.get(DcMotor.class, "spatedreapta");

        spatestanga.setDirection(DcMotorSimple.Direction.REVERSE);
        fatastanga.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void mutautilajul(Gamepad gamepad1) {
        double vertical = -gamepad1.left_stick_y;
        double orizontal = gamepad1.left_stick_x;
        double pivot = gamepad1.right_stick_x;

        double spatestangaPower = vertical - orizontal - pivot;
        double spatedreaptaPower = vertical + orizontal + pivot;
        double fatadreaptaPower = vertical - orizontal + pivot;
        double fatastangaPower = vertical + orizontal - pivot;
        double maxPower = Math.max(Math.abs(fatastangaPower), Math.abs(fatadreaptaPower));
        maxPower = Math.max(maxPower, Math.abs(spatestangaPower));
        maxPower = Math.max(maxPower, Math.abs(spatedreaptaPower));

        if (maxPower > 1.0) {
            fatastangaPower /= maxPower;
            fatadreaptaPower /= maxPower;
            spatestangaPower /= maxPower;
            spatedreaptaPower /= maxPower;
        }

        fatadreapta.setPower(fatadreaptaPower);
        fatastanga.setPower(fatastangaPower);
        spatestanga.setPower(spatestangaPower);
        spatedreapta.setPower(spatedreaptaPower);
    }
}