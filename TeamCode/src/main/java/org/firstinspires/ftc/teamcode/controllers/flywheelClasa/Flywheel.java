package org.firstinspires.ftc.teamcode.controllers.flywheelClasa;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Flywheel
{
    private DcMotor m1,m2;
    private double kp=0.16,kv=0.000488,ks=0.17;  // trb sa le gasim
    private double encodercpm=33;

    private void init(HardwareMap hwMap)
    {
        m1=hwMap.get(DcMotor.class, "motorstanga");
        m2=hwMap.get(DcMotor.class, "motordreapta");
        m1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        m2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setMotorPower(double power){
        m1.setPower(power);
        m2.setPower(power);
    }
    public void stopjoc(){
        m1.setPower(0);
        m2.setPower(0);
    }

    public void setMotorRPM(double targetrpm){
        double eroare=targetrpm-getRPM();
        double ff=(kv*targetrpm)+ks;
        double fb=eroare*kp;
        double power=ff+fb;

        setMotorPower(power);
    }

    public double getTicks(){
        return  m1.getVelocity();
    }

    public void getRPM(){
        return ((getTicks()/encodercpm)*60)
    }
}
