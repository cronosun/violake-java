package com.github.caelis.violake.example.app.views.example4;

import com.github.caelis.violake.android.Applicator;
import com.github.caelis.violake.android.Event;
import com.github.caelis.violake.android.Violake;
import com.github.caelis.violake.android.ext.FocusOnAttach;
import com.github.caelis.violake.android.ext.GetChecked;
import com.github.caelis.violake.android.ext.GetClick;
import com.github.caelis.violake.android.ext.GetText;
import com.github.caelis.violake.android.ext.SetChecked;
import com.github.caelis.violake.android.ext.SetEditorText;
import com.github.caelis.violake.android.ext.SetEnabled;
import com.github.caelis.violake.android.ext.SetTextInputLayoutError;
import com.github.caelis.violake.android.ext.SetVisibility;
import com.github.caelis.violake.android.ext.Visibility;
import com.github.caelis.violake.core.Disposable;
import com.github.caelis.violake.core.Unit;
import com.github.caelis.violake.example.core.example4.Example4Model;

import io.reactivex.Flowable;

public final class Example4ViewApplicator implements Applicator<Example4View, Example4Model> {

    private static final Example4ViewApplicator INSTANCE = new Example4ViewApplicator();

    public static Example4ViewApplicator get() {
        return INSTANCE;
    }

    @Override
    public Disposable apply(
            Violake violake,
            Event event,
            Example4View target,
            Example4Model data) {

        return violake.chain(
                // first name
                violake.apply(FocusOnAttach.get(), target.getFirstNameLayout(), Unit.get()),
                violake.apply(GetText.get(), target.getFirstName(), data::setFirstName),
                violake.apply(SetTextInputLayoutError.get(), target.getFirstNameLayout(), data.firstNameError()),
                violake.apply(SetEditorText.get(), target.getFirstName(), data.getFirstName()),
                // last name
                violake.apply(GetText.get(), target.getLastName(), data::setLastName),
                violake.apply(SetTextInputLayoutError.get(), target.getLastNameLayout(), data.lastNameError()),
                violake.apply(SetEditorText.get(), target.getLastName(), data.getLastName()),
                // email
                violake.apply(GetText.get(), target.getEMail(), data::setEMail),
                violake.apply(SetTextInputLayoutError.get(), target.getEMailLayout(), data.eMailError()),
                violake.apply(SetEditorText.get(), target.getEMail(), data.getEMail()),
                // phone visibility
                violake.apply(SetVisibility.get(), target.getPhoneLayout(),
                        Flowable.fromPublisher(data.isPhoneEntryVisible())
                                .map(visible -> visible ? Visibility.VISIBLE : Visibility.GONE)),
                violake.apply(GetChecked.get(), target.getIncludePhone(), data::setIncludePhone),
                violake.apply(SetChecked.get(), target.getIncludePhone(), data.getIncludePhone()),
                // phone
                violake.apply(GetText.get(), target.getPhone(), data::setPhone),
                violake.apply(SetTextInputLayoutError.get(), target.getPhoneLayout(), data.phoneError()),
                violake.apply(SetEditorText.get(), target.getPhone(), data.getPhone()),
                // reset
                violake.apply(GetClick.get(), target.getReset(), data::reset),
                // submit
                violake.apply(SetEnabled.get(), target.getSubmit(), data.canSubmit())
        );
    }
}
