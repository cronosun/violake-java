package com.github.caelis.violake.example.app.views.example4;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Switch;

import com.github.caelis.violake.example.app.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

public class Example4View extends ConstraintLayout {

    private TextInputLayout firstNameLayout;
    private TextInputEditText firstName;
    private TextInputLayout lastNameLayout;
    private TextInputEditText lastName;
    private TextInputLayout eMailLayout;
    private TextInputEditText eMail;
    private MaterialButton reset;
    private MaterialButton submit;

    private Switch includePhone;

    private TextInputLayout phoneLayout;
    private TextInputEditText phone;

    public Example4View(Context context) {
        super(context);
        init();
    }

    public Example4View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Example4View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View.inflate(this.getContext(), R.layout.example4_view_layout, this);
        this.firstNameLayout = findViewById(R.id.example4_first_name_layout);
        this.firstName = findViewById(R.id.example4_first_name);

        this.lastNameLayout = findViewById(R.id.example4_last_name_layout);
        this.lastName = findViewById(R.id.example4_last_name);

        this.eMailLayout = findViewById(R.id.example4_email_layout);
        this.eMail = findViewById(R.id.example4_email);

        this.includePhone = findViewById(R.id.example_4_include_phone);

        this.phoneLayout = findViewById(R.id.example4_phone_layout);
        this.phone = findViewById(R.id.example4_phone);

        this.submit = findViewById(R.id.example4_submit);
        this.reset = findViewById(R.id.example4_reset);
    }

    public TextInputLayout getFirstNameLayout() {
        return firstNameLayout;
    }

    public TextInputEditText getFirstName() {
        return firstName;
    }

    public Switch getIncludePhone() {
        return includePhone;
    }

    public TextInputLayout getPhoneLayout() {
        return phoneLayout;
    }

    public TextInputEditText getPhone() {
        return phone;
    }

    public TextInputLayout getLastNameLayout() {
        return lastNameLayout;
    }

    public TextInputEditText getLastName() {
        return lastName;
    }

    public TextInputLayout getEMailLayout() {
        return eMailLayout;
    }

    public TextInputEditText getEMail() {
        return eMail;
    }

    public MaterialButton getReset() {
        return reset;
    }

    public MaterialButton getSubmit() {
        return submit;
    }
}
