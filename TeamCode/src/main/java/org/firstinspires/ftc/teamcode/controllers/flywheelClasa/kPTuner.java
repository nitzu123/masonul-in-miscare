package org.firstinspires.ftc.teamcode.controllers.flywheelClasa;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class kPTuner extends OpMode {

    Flywheel flywheel=new Flywheel();

    public static double kV=0.01;
    public double kS=0.17, kP=0.0;//trb sa gasim valoarea
    double[] valori={0.000001,0.00001,0.0001,0.001,0.01};
    int i=2;
    public double goalRPM=1500;  //si pe asta ttrb sa l calc de pe site

    @Override
    public void init(){
        flywheel.init(hardwareMap);
    }

    @Override
    public void loop(){

        if(gamepad1.a)goalRPM=1500;
        if(gamepad1.b)goalRPM=800;
        if(gamepad1.dpadRightWasPressed() && i<4)i++;
        if(gamepad1.dpadLeftWasPressed() && i>0)i--;

        double curent=valori[i];
        if(gamepad1.dpadUpWasPressed())kP+=curent;
        if(gamepad1.dpadDownWasPressed())kP-=curent;

        double feedforward=(kV*goalRPM)*kS;
        double eroare=goalRPM- flywheel.getRPM();
        double feedback=eroare*kP;
        flywheel.setMotorPower(feedback+feedforward);

        telemetry.addData("Valoare","%.6f",curent);
        telemetry.addData("kP","%.6f",kP);
        telemetry.addData("Eroare",eroare);
        telemetry.addData("RPM",flywheel.getRPM());
}
