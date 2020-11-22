package pl.javastart.weekop.dao;

import pl.javastart.weekop.exception.NoSuchDbTypeException;

public abstract class DAOFactory {
	
	public static final int MYSQL_DAO_FACTORY = 1;
	
	
	public abstract DiscoveryDAO getDiscoveryDAO();
	public abstract UserDAO getUserDAO();
	public abstract VoteDAO getVoteDAO();

	/* Okej wiec wywołujemy abstrakcyjną metodę ktra trzyma w całośći całą baze danych 
	 * jest to metoda anstrakcyjna jedynie spinajaca nam logike w jednym miejscu 
	 * stad mamt mysql dao facktor czyli wydziedziczoną instancjię dla bazy MySQL 
	 * 
	 * jezeli mielibyśmy cos innego np OracleSQL to tworzymysmy dodatkowy case w metodzie getDAOFactor(int type)
	 */
	public static DAOFactory getDAOFactory() {
		DAOFactory factory = null;
		try {
			factory = getDAOFactory(MYSQL_DAO_FACTORY);
		} catch (NoSuchDbTypeException e) {
			e.printStackTrace();
		}
		return factory;
	}
	
	private static DAOFactory getDAOFactory(int type) throws NoSuchDbTypeException {
		switch (type) {
		case MYSQL_DAO_FACTORY:
			return new MysqlDAOFactory();
		default:
			throw new NoSuchDbTypeException();
		}
	}
}
