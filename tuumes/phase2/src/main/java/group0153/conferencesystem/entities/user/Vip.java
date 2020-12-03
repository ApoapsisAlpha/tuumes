package group0153.conferencesystem.entities.user;

public class Vip extends User {
    /**
     * Constructor that initiates a Vip instance.
     *
     * @param name     name of the vip.
     * @param email    email of the vip.
     * @param password password of the vip account.
     */
    public Vip(String name, String email, String password) {
        super(name, email, password);
        type = UserType.VIP;
    }
}
