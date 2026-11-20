package org.firstinspires.ftc.teamcode.controllers.flywheelClasa;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class kSTuner extends OpMode {

    Flywheel flywheel=new Flywheel();

    public static double kS=0;
    double[] valori={0.000001,0.00001,0.0001,0.001,0.01};
    int i=3;

    @Override
    public void init(){
        flywheel.init(hardwareMap);
    }

    @Override
    public void loop(){

        if(gamepad1.dpadRightWasPressed() && i<4)i++;
        if(gamepad1.dpadLeftWasPressed() && i>0)i--;

        double curent=valori[i];
        if(gamepad1.dpadUpWasPressed())kS+=curent;
        if(gamepad1.dpadDownWasPressed())kS-=curent;

        flywheel.setMotorPower(kS);

        telemetry.addData("Valoare","%.6f",curent);
        telemetry.addData("kS","%.6f",kS);
        telemetry.addData("RPM",flywheel.getRPM());
        telemetry.addData("Ticks per second",flywheel.getTicks());

    }
}
