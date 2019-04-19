/*
 * package com.ojas.obs.facade;
 * 
 * import static org.junit.Assert.assertEquals; import static
 * org.mockito.Mockito.mock; import static org.mockito.Mockito.when;
 * 
 * import java.lang.reflect.Field; import java.util.ArrayList; import
 * java.util.List;
 * 
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import org.junit.Before; import org.junit.Test; import
 * org.mockito.InjectMocks; import org.mockito.Mock; import org.mockito.Spy;
 * import org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity;
 * 
 * import com.ojas.obs.dao.SkillDao; import com.ojas.obs.model.ErrorResponse;
 * import com.ojas.obs.model.Skill; import com.ojas.obs.request.SkillRequest;
 * 
 * public class SkillFacadeTest {
 * 
 * 
 * @Mock private SkillDao skillDao;
 * 
 * @InjectMocks private SkillFacadeImpl skillFacadeImpl;
 * 
 * @Spy SkillRequest skillRequest;
 * 
 * @Spy ErrorResponse error = new ErrorResponse();
 * 
 * @Spy ResponseEntity<Object> objEntity = new ResponseEntity<Object>(error,
 * HttpStatus.UNPROCESSABLE_ENTITY);
 * 
 * @Spy ResponseEntity<Object> sucessResponse = new
 * ResponseEntity<Object>(error, HttpStatus.OK);
 * 
 * @Spy List<Skill> costCenterList = new ArrayList<Skill>();
 * 
 * @Before public void init() { skillFacadeImpl = new SkillFacadeImpl();
 * skillDao = mock(SkillDao.class); setCollabarator(skillFacadeImpl, "skillDao",
 * skillDao); }
 * 
 * public void setCollabarator(Object object, String name, Object collabarator)
 * { Field field; try { field = object.getClass().getDeclaredField(name);
 * field.setAccessible(true); field.set(object, collabarator); } catch
 * (Exception e) { e.printStackTrace(); } }
 * 
 * public List<Skill> getSkill() { ArrayList<Skill> arrayList = new
 * ArrayList<>(); Skill skill = new Skill(); skill.setId(1);
 * skill.setSkill_id(123); skill.setSkill_name("java"); Skill skill1 = new
 * Skill(); skill1.setId(2); skill1.setSkill_id(124); skill1.setSkill_name("c");
 * 
 * arrayList.add(skill); arrayList.add(skill1); return arrayList; }
 * 
 * //-------set save method-------------
 * 
 * @Test public void setSkillTest() throws Exception {
 * skillRequest.setListOfSkill(this.getSkill());
 * skillRequest.setTransactionType("save"); HttpServletRequest request = null;
 * HttpServletResponse response = null;
 * when(skillDao.saveSkillInfo(skillRequest)).thenReturn(1);
 * 
 * ResponseEntity<Object> setSkill = skillFacadeImpl.setSkillInfo(skillRequest);
 * HttpStatus statusCode = setSkill.getStatusCode(); assertEquals(HttpStatus.OK,
 * statusCode); }
 * 
 * 
 * }
 */