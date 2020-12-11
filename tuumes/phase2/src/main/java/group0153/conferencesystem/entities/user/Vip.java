package group0153.conferencesystem.entities.user;

/**
 * An entity class for VIP user, subclass of User.
 * Vip class for a user who is a VIP user.
 */
public class Vip extends User {
    /**
     * Constructor that initiates a Vip instance.
     *
     * @param id       id of the vip
     * @param name     name of the vip.
     * @param email    email of the vip.
     * @param password password of the vip account.
     */
    public Vip(String id, String name, String email, String password) {
        super(id, name, email, password);
        type = UserType.VIP;
    }
}
