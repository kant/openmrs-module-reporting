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

import org.openmrs.Cohort;
import org.openmrs.Location;
import org.openmrs.annotation.Handler;
import org.openmrs.api.PatientSetService.PatientLocationMethod;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.toreview.LocationCohortDefinition;
import org.openmrs.module.reporting.evaluation.EvaluationContext;

/**
 * This interfaces provides the functionality to evaluate a CohortDefinition and return a Cohort.
 */
@Handler(supports={LocationCohortDefinition.class})
public class LocationCohortDefinitionEvaluator implements CohortDefinitionEvaluator {

	/**
	 * Default Constructor
	 */
	public LocationCohortDefinitionEvaluator() {}
	
	/**
     * @see CohortDefinitionEvaluator#evaluateCohort(CohortDefinition, EvaluationContext)
     */
    public Cohort evaluate(CohortDefinition cohortDefinition, EvaluationContext context) {
    	
    	Cohort c = new Cohort();
    	
    	LocationCohortDefinition lcd = (LocationCohortDefinition) cohortDefinition;
    	if (lcd.getLocations() != null) { 
	    	for (Location l : lcd.getLocations()) {
	        	PatientLocationMethod method = lcd.getCalculationMethod();
	        	c = Cohort.union(c, Context.getPatientSetService().getPatientsHavingLocation(l, method));
	    	}
    	}
    	return c;
    }
}