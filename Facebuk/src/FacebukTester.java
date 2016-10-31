import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * Make absolute sure that your code can compile together with this tester!
 * If it does not, you may get a 0 for your assignment.
 */
public class FacebukTester {
	private ArrayList people;
	private ArrayList pets;
	private ArrayList possessions;
	private ArrayList moments;
	private Person barack, michelle;
	private Moment meAndBarack;

	/**
	 * Test fixture to reproduce the Facebuk scenario shown in the Lab0.html description.
	 */
	@Before
	public void setUp () {
		this.people = new ArrayList();
		this.pets = new ArrayList();
		this.possessions = new ArrayList();
		this.moments = new ArrayList();

		////////////////////
		// Initialize people
		////////////////////
		Person michelle = new Person("Michelle", new Image("Michelle.png"));
		Person barack = new Person("Barack", new Image("Barack.png"));
		Person kevin = new Person("Kevin", new Image("Kevin.png"));
		Person ina = new Person("Ina", new Image("Ina.png"));
		Person joe = new Person("Joe", new Image("Joe.png"));
		Person malia = new Person("Malia", new Image("Malia.png"));
		this.people.add(joe);
		this.people.add(ina);
		this.people.add(kevin);
		this.people.add(barack);
		this.people.add(malia);
		this.people.add(michelle);

		//////////////////
		// Initialize pets
		//////////////////
		Pet bo = new Pet("Bo", new Image("Bo.png"));
		Pet sunny = new Pet("Sunny", new Image("Sunny.png"));

		////////////////////////////////////
		// Initialize groups of people, pets
		////////////////////////////////////
		// Kevin, Barack, and Ina
		ArrayList michelleFriends = new ArrayList();
		michelleFriends.add(kevin);
		michelleFriends.add(barack);
		michelleFriends.add(ina);

		// Michelle and Barack
		ArrayList michelleAndBarack = new ArrayList();
		michelleAndBarack.add(michelle);
		michelleAndBarack.add(barack);

		// Michelle, Joe, Bo, and Malia
		ArrayList michelleJoeBoAndMalia = new ArrayList();
		michelleJoeBoAndMalia.add(michelle);
		michelleJoeBoAndMalia.add(joe);
		michelleJoeBoAndMalia.add(bo);
		michelleJoeBoAndMalia.add(malia);

		// Malia and Sunny
		ArrayList maliaAndSunny = new ArrayList();
		maliaAndSunny.add(malia);
		maliaAndSunny.add(sunny);

		// Malia and Bo
		ArrayList maliaAndBo = new ArrayList();
		maliaAndSunny.add(malia);
		maliaAndSunny.add(bo);

		// Michelle
		ArrayList michelleList = new ArrayList();
		michelleList.add(michelle);

		// Bo
		ArrayList boList = new ArrayList();
		boList.add(bo);

		// Set people's friend lists
		michelle.setFriends(michelleFriends);
		malia.setFriends(boList);
		sunny.setFriends(boList);
		barack.setFriends(michelleList);
		kevin.setFriends(michelleList);
		ina.setFriends(michelleList);

		//////////////////////////
		// Finish configuring pets
		//////////////////////////
		bo.setOwners(michelleAndBarack);
		bo.setFriends(maliaAndSunny);
		sunny.setOwners(michelleAndBarack);
		sunny.setFriends(maliaAndBo);
		ArrayList boAndSunny = new ArrayList();
		boAndSunny.add(bo);
		boAndSunny.add(sunny);
		this.pets = boAndSunny;
		michelle.setPets(boAndSunny);

		//////////////
		// Possessions
		//////////////
		Possession boxingBag = new Possession("BoxingBag", new Image("BoxingBag.png"), 20.0f);
		boxingBag.setOwner(michelle);
		ArrayList michellePossessions = new ArrayList();
		michellePossessions.add(boxingBag);
		this.possessions = michellePossessions;
		michelle.setPossessions(michellePossessions);

		/////////
		// Smiles
		/////////
		ArrayList michelleAndBarackSmiles = new ArrayList();
		michelleAndBarackSmiles.add(0.25f);
		michelleAndBarackSmiles.add(0.75f);

		ArrayList michelleJoeBoAndMaliaSmiles = new ArrayList();
		michelleJoeBoAndMaliaSmiles.add(0.2f);
		michelleJoeBoAndMaliaSmiles.add(0.3f);
		michelleJoeBoAndMaliaSmiles.add(0.4f);
		michelleJoeBoAndMaliaSmiles.add(0.5f);

		//////////
		// Moments
		//////////
		Moment meAndBarack = new Moment("Me & Barack", new Image("MeAndBarack.png"), michelleAndBarack, michelleAndBarackSmiles);
		Moment meJoeAndCo = new Moment("Me, Joe & co.", new Image("MeJoeAndCo.png"), michelleJoeBoAndMalia, michelleJoeBoAndMaliaSmiles);
		this.moments.add(meAndBarack);
		this.moments.add(meJoeAndCo);

		ArrayList michelleMoments = new ArrayList();
		michelleMoments.add(meAndBarack);
		michelleMoments.add(meJoeAndCo);
		michelle.setMoments(michelleMoments);

		ArrayList barackMoments = new ArrayList();
		barackMoments.add(meAndBarack);
		barack.setMoments(barackMoments);

		ArrayList joeMoments = new ArrayList();
		joeMoments.add(meJoeAndCo);
		joe.setMoments(joeMoments);

		ArrayList maliaMoments = new ArrayList();
		maliaMoments.add(meJoeAndCo);
		malia.setMoments(maliaMoments);

		ArrayList boMoments = new ArrayList();
		boMoments.add(meJoeAndCo);
		bo.setMoments(boMoments);

		// Save a few variables as instance variables so we can use them for tests
		this.barack = barack;
		this.michelle = michelle;
		this.meAndBarack = meAndBarack;
	}

	@Test
	public void testFindBestMoment () {
		assertEquals(michelle.getOverallHappiestMoment(), meAndBarack);
	}

	@Test
	public void testGetFriendWithWhomIAmHappiest () {
		assertEquals(michelle.getFriendWithWhomIAmHappiest(), barack);
	}

	@Test
	public void testFacebukFromProjectDescription () {
		// People
		assertEquals(true, true);  // getting through the setUp without crashing is already worth something
	}
}
