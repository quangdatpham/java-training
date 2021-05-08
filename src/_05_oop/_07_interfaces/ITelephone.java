package _05_oop._07_interfaces;

public interface ITelephone {
    void powerOn();

    void dial(int phoneNumber);

    void answer();

    boolean callPhone(int phoneNumber);

    boolean isRinging();
}
