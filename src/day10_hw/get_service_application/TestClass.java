package day10_hw.get_service_application;

@Service
public class TestClass {

    private String status = "Not initialize";

    @InitService // if delete this in console will be Not initialize
    public void init() {
        status = "Initialize";
    }

    public String getStatus() {
        return status;
    }
}
