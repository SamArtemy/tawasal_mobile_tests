package Utils;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.DisableableFeature.PARAMETER_FORMATTING;
import static org.aeonbits.owner.Config.DisableableFeature.VARIABLE_EXPANSION;

@Config.DisableFeature({VARIABLE_EXPANSION, PARAMETER_FORMATTING})
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:capabilities/Pixel_3a_API_30_x86.properties"})
public interface PlatformConfig extends Config{

    @Config.Key("platformName")
    String platformName();

    @Config.Key("platformVersion")
    String platformVersion();

    @Config.Key("deviceName")
    String deviceName();

    @Config.Key("udid")
    String udid();

    @Config.Key("avd")
    String avd();

    @Config.Key("appPackage")
    String appPackage();

    @Config.Key("appActivity")
    String appActivity();
}
