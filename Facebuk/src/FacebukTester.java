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
	private Person barack;
	private static Person michelle;
	private Moment meAndBarack;

	/**
	 * Test fixture to reproduce the Facebuk scenario shown in the Lab0.html description.
	 */
	@Before
	public void setUp () {
		people = new ArrayList();
		pets = new ArrayList();
		possessions = new ArrayList();
		moments = new ArrayList();

		Person michelle = new Person("Michelle", new Image("Michelle.png"));
		Person barack = new Person("Barack", new Image("Barack.png")); 
		Person kevin = new Person("Kevin", new Image("Kevin.png"));
		Person ina = new Person("Ina", new Image("Ina.png"));
		Person joe = new Person("Joe", new Image("Joe.png"));
		Person malia = new Person("Malia", new Image("Malia.png"));

		ArrayList michelleFriends = new ArrayList();
		michelleFriends.add(kevin);
		michelleFriends.add(barack);
		michelleFriends.add(ina);
		michelle.setFriends(michelleFriends);

		// M&B
		ArrayList michelleAndBarack = new ArrayList();
		michelleAndBarack.add(michelle);
		michelleAndBarack.add(barack);

		// Pets
		Pet bo = new Pet("Bo", new Image("Bo.png")); 
		Pet sunny = new Pet("Sunny", new Image("Sunny.png"));

		// M&S
		ArrayList maliaAndSunny = new ArrayList();
		maliaAndSunny.add(malia);
		maliaAndSunny.add(sunny);

		// M&B
		ArrayList maliaAndBo = new ArrayList();
		maliaAndSunny.add(malia);
		maliaAndSunny.add(bo);

		// Finish configuring pets
		bo.setOwners(michelleAndBarack);
		bo.setFriends(maliaAndSunny);
		sunny.setOwners(michelleAndBarack);
		sunny.setFriends(maliaAndBo);

		// Possessions
		Possession boxingBag = new Possession("BoxingBag", new Image("BoxingBag.png"), 20.0f); 
		boxingBag.setOwner(michelle);

		// Groups of people and their smiles
		ArrayList michelleAndBarackSmiles = new ArrayList();
		michelleAndBarackSmiles.add(0.25f);
		michelleAndBarackSmiles.add(0.75f);

		ArrayList michelleJoeBoAndMalia = new ArrayList();
		michelleJoeBoAndMalia.add(michelle);
		michelleJoeBoAndMalia.add(joe);
		michelleJoeBoAndMalia.add(bo);
		michelleJoeBoAndMalia.add(malia);

		ArrayList michelleJoeBoAndMaliaSmiles = new ArrayList();
		michelleJoeBoAndMaliaSmiles.add(0.2f);
		michelleJoeBoAndMaliaSmiles.add(0.3f);
		michelleJoeBoAndMaliaSmiles.add(0.4f);
		michelleJoeBoAndMaliaSmiles.add(0.5f);

		// Moments
		Moment meAndBarack = new Moment("Me & Barack", new Image("MeAndBarack.png"), michelleAndBarack, michelleAndBarackSmiles);
		Moment meJoeAndCo = new Moment("Me, Joe & co.", new Image("MeJoeAndCo.png"), michelleJoeBoAndMalia, michelleJoeBoAndMaliaSmiles);

		moments.add(meAndBarack);
		moments.add(meJoeAndCo);

		michelle.setMoments(moments);

		pets.add(bo);
		pets.add(sunny);

		people.add(joe);
		people.add(ina);
		people.add(kevin);
		people.add(barack);
		people.add(malia);
		people.add(michelle);

		this.barack = barack;
		this.michelle = michelle;
		this.meAndBarack = meAndBarack;

		possessions.add(boxingBag); 
	}

	@Test
	public void testFindBestMoment () {
		assertEquals(michelle.getHappiestMoment(), meAndBarack);
	}

	//@Test
	//public void testGetFriendWithWhomIAmHappiest () {
	//	assertEquals(michelle.getFriendWithWhomIAmHappiest(), barack);
	//}

	@Test
	public void testFacebukFromProjectDescription () {
		// People
		assertEquals(true, true);  // getting through the setUp without crashing is already worth something
	}
}
