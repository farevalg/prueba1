package nej.testUnit;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import comun2.persistence.DataException;
import nej.persistence.DO.gestPersona.AdmPersonaDTO;
import nej.persistence.VO.gestPersona.AdmPersonaVO;
import nej.wsrest.servicios.PerceptorRestService;

@RunWith(MockitoJUnitRunner.class)
public class PerceptorServiceTest2 {

	@Mock
	AdmPersonaDTO admDTO = new AdmPersonaDTO();
	
	@InjectMocks
	PerceptorRestService  servMokeado  = new PerceptorRestService();
	
	public void setup() {
	}

	@Test
	public void testListaPerceptores(){
		
		
		PerceptorRestService  serv  = new PerceptorRestService(); 
		List<AdmPersonaVO> response = serv.getListaPerceptores("APELLIDO1", "AR%");
		Assert.assertNotNull(response);
		for (int i=0; i<response.size(); i++) {
			assertThat(response.get(i), instanceOf(AdmPersonaVO.class));
			Assert.assertTrue(response.get(i).getApellido1().substring(0,2).equalsIgnoreCase("ar"));
		}
		
	}
	

	@Test
	public void test2() throws DataException {
		List<AdmPersonaVO> listaMockeada = new ArrayList<AdmPersonaVO>();
		AdmPersonaVO vo = new AdmPersonaVO();
		vo.setApellido1("ARENILLAS");
		listaMockeada.add(vo);
		
		vo = new AdmPersonaVO();
		vo.setApellido1("ARGUELLO");
		listaMockeada.add(vo);
		
		when(admDTO.searchbyPersonaPerceptor(any(String.class), any(String.class))).thenReturn(listaMockeada);
		
		 
		List<AdmPersonaVO> response = servMokeado.searchbyPersonaPerceptor("APELLIDO1", "AR%");
		Assert.assertNotNull(response);
		for (int i=0; i<response.size(); i++) {
			assertThat(response.get(i), instanceOf(AdmPersonaVO.class));
			Assert.assertTrue(response.get(i).getApellido1().substring(0,2).equalsIgnoreCase("ar"));
		}
	}
	
}
