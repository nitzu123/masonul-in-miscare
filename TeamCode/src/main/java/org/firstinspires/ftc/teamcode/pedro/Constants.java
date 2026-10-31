package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {

    // Configurația motoarelor pentru șasiul Mecanum
    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("fatastanga");
        c.frontRightName.set("fatadreapta");
        c.backLeftName.set("spatestanga");
        c.backRightName.set("spatedreapta");
        c.frontLeftDirection.set(DcMotorSimple.Direction.FORWARD);
        c.frontRightDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backLeftDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backRightDirection.set(DcMotorSimple.Direction.REVERSE);
        c.manualBrakeMode.set(true);
    });

    // Configurația completă pentru localizatorul goBILDA Pinpoint (ForesightTuner)
    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(6.042088786448081);
        c.yPodOffset.set(-3.823979595514733);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });
    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.22373588256757737);
                Controller secondaryTranslationalForward = Controller.proportional(0.0826644302758331);
                Controller primaryTranslationalLateral = Controller.proportional(0.3494810657338071);
                Controller secondaryTranslationalLateral = Controller.proportional(0.12912391548257932);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.011980538268600432));
                c.brake.set(Controller.proportionalFeedforward(0.010183457528310368));

                c.headingFeedback.set(Controller.proportional(2.783747759062979));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.04956242936402167, 0.007420760665570658));

                c.linearBrakeCoefficients.set(Matrix.diag(0.10363923694895212, 0.07966172316662931));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0017604410172074828, 0.0020467441297256795));

                c.maxAchievableForwardVelocity.set(64.91170201618621);
                c.maxAchievableStrafeVelocity.set(51.50453849507902);
                c.naturalForwardDeceleration.set(29.98774543269558);
                c.naturalStrafeDeceleration.set(40.74376220826806);
            }
    );
    public static Follower create(HardwareMap h) {
        return new Follower(
                new PinpointLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }
}