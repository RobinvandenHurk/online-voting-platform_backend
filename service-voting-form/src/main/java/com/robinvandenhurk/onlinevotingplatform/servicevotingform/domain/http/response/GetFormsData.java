package com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.response;

import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.Form;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: GetCurrentUserData
 */

public class GetFormsData extends HttpResponseData {

    private List<SimpleForm> forms;

    public GetFormsData(List<Form> forms) {
        this.forms = new ArrayList<>();

        forms.forEach(form -> this.forms.add(new SimpleForm(form.getId(), form.getName())));
    }

    public List<SimpleForm> getForms() {
        return forms;
    }

    private class SimpleForm {

        private int id;
        private String name;

        public SimpleForm(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
