package nej.testUnit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import comun2.persistence.DataException;
import nej.persistence.DO.gestPersona.AdmPersonaDTO;
import nej.persistence.VO.gestPersona.AdmPersonaVO;
import nej.wsrest.servicios.PerceptorRestService;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class PerceptorServiceTest {

	@Mock
	AdmPersonaDTO adm = new AdmPersonaDTO();
	
	@Before 
	public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

	public void setup() {
	}

	@Test
	public void testListaPerceptores() throws Exception{
		
		List<AdmPersonaVO> lista = new ArrayList();
		lista.add(new AdmPersonaVO());
		lista.get(0).setApellido1("ARGUELLO");
		
		when(adm.search(any(String.class).toString())).thenReturn(lista);
		//when(adm.search("AR%")).thenReturn(lista);
		
		
		PerceptorRestService  serv  = new PerceptorRestService(); 
		List<AdmPersonaVO> response = serv.getListaPerceptores("APELLIDO1", "AR%");
		Assert.assertNotNull(response);
		for (int i=0; i<response.size(); i++) {
			assertThat(response.get(i), instanceOf(AdmPersonaVO.class));
			Assert.assertTrue(response.get(i).getApellido1().substring(0,2).equalsIgnoreCase("ar"));
		}
		
	}
	
	
}
