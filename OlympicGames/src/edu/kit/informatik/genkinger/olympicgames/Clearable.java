package edu.kit.informatik.genkinger.olympicgames;

/**
 * This interface is used in Objects that should be able to be cleared.
 *
 * @see edu.kit.informatik.genkinger.olympicgames.containers.AdminContainer
 * @see edu.kit.informatik.genkinger.olympicgames.containers.AthleteContainer
 * @see edu.kit.informatik.genkinger.olympicgames.containers.CompetitionContainer
 * @see edu.kit.informatik.genkinger.olympicgames.containers.IocContainer
 * @see edu.kit.informatik.genkinger.olympicgames.containers.SportContainer
 * @see edu.kit.informatik.genkinger.olympicgames.containers.VenueContainer
 */
public interface Clearable {
    /**
     *
     */
    void clear();
}
