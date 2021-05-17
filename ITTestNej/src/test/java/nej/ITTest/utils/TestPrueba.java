package nej.ITTest.utils;

import org.junit.Test;

import comun2.persistence.DataException;
import junit.framework.Assert;
import nej.persistence.DO.gestPersona.AdmPersonaDTO;

public class TestPrueba {

	@Test
	public void test1() throws DataException {
		AdmPersonaDTO admPersonaDTO= new AdmPersonaDTO();
		Assert.assertTrue(admPersonaDTO.findbyPrimaryKey("kkjk=1")!=null);
	}
}
