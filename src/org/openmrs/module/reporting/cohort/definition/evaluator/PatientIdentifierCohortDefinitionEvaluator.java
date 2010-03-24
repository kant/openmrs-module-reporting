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
package org.openmrs.module.reporting.cohort.definition.evaluator;

import java.util.Date;
import java.util.List;

import org.openmrs.Cohort;
import org.openmrs.Patient;
import org.openmrs.annotation.Handler;
import org.openmrs.api.PatientService;
import org.openmrs.api.PatientSetService;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.toreview.EncounterCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.toreview.PatientIdentifierCohortDefinition;
import org.openmrs.module.reporting.evaluation.EvaluationContext;

/**
 * Evaluates an EncounterCohortDefinition and produces a Cohort
 */
@Handler(supports={PatientIdentifierCohortDefinition.class})
public class PatientIdentifierCohortDefinitionEvaluator implements CohortDefinitionEvaluator {

	/**
	 * Default Constructor
	 */
	public PatientIdentifierCohortDefinitionEvaluator() {}
	
	/**
     * @see CohortDefinitionEvaluator#evaluateCohort(CohortDefinition, EvaluationContext)
     */
    public Cohort evaluate(CohortDefinition cohortDefinition, EvaluationContext context) {
    	PatientIdentifierCohortDefinition picd = (PatientIdentifierCohortDefinition) cohortDefinition;
		
		PatientService ps = Context.getPatientService();
		List<Patient> patients = ps.getPatients("", "", picd.getPatientIdentifierTypes(), false);
    	
		return new Cohort(patients);		
    }
}