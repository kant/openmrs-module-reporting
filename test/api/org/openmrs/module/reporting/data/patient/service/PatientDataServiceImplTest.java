/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.reporting.data.patient.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.data.patient.PatientData;
import org.openmrs.module.reporting.data.patient.definition.PatientDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientIdDataDefinition;
import org.openmrs.module.reporting.evaluation.EvaluationContext;
import org.openmrs.test.BaseModuleContextSensitiveTest;

/**
 * Test the PatientDataServiceImpl
 */
public class PatientDataServiceImplTest extends BaseModuleContextSensitiveTest {
	
	/**
	 * @see PatientDataServiceImpl#evaluate(PatientData,EvaluationContext)
	 * @verifies evaluate a patient query
	 */
	@Test
	public void evaluate_shouldEvaluateAnPatientData() throws Exception {
		PatientDataDefinition definition = new PatientIdDataDefinition();
		PatientData data = Context.getService(PatientDataService.class).evaluate(definition, new EvaluationContext());
		Assert.assertNotNull(data);
	}
	
	/**
	 * @see PatientDataServiceImpl#saveDefinition(PatientData)
	 * @verifies save a patient query
	 */
	@Test
	public void saveDefinition_shouldSaveAnPatientData() throws Exception {
		PatientDataDefinition definition = new PatientIdDataDefinition();
		definition.setName("All Patient Ids");
		definition = Context.getService(PatientDataService.class).saveDefinition(definition);
		Assert.assertNotNull(definition.getId());
		Assert.assertNotNull(definition.getUuid());
		PatientDataDefinition loadedDefinition = Context.getService(PatientDataService.class).getDefinitionByUuid(definition.getUuid());
		Assert.assertEquals(definition, loadedDefinition);
	}
	
	@Before
	public void setup() throws Exception {
		executeDataSet("org/openmrs/module/reporting/include/ReportTestDataset.xml");
	}
}