package org.firstinspires.ftc.teamcode.controllers;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="program principal")
public class main extends OpMode {
    private controllerMecanum sasiu;
    private controllerServomasonic echercompas;

    public void init(){
        sasiu=new controllerMecanum(hardwareMap);
        echercompas= new controllerServomasonic(hardwareMap);
    }
    @Override
    public void loop(){
        sasiu.mutautilajul(gamepad1);
        echercompas.misca(gamepad1);
    }
}