function validate()
{
    var dob = new Date(document.signupForm.vdob.value);
    var today = new Date();
    
    if(document.signupForm.vname.value == "")
    {
        alert("Please Enter Your Name");
        document.signupForm.vname.focus();
        return false;
    }
    else if(document.signupForm.vid.value == "")
    {
        alert("Please Enter ID Card #");
        document.signupForm.vid.focus();
        return false;
    }
    else if(document.signupForm.vdob.value == "")
    {
        alert("Please Enter DOB");
        document.signupForm.vdob.focus();
        return false;
    }
    else if(today.getFullYear()-dob.getFullYear() < 18)
    {
        alert("Minimum age 18 required");
        document.signupForm.vdob.focus();
        return false;
    }
    if((today.getFullYear()-dob.getFullYear() == 18) && ((today.getMonth()+1)-(dob.getMonth()+1) < 0))
    {
        alert("Minimum age 18 required");
        document.signupForm.vdob.focus();
        return false;
    }
    else if(document.signupForm.vconst_no.value == "")
    {
        alert("Please Enter Password");
        document.signupForm.vconst_no.focus();
        return false;
    }
    else if(document.signupForm.vpassword.value == "")
    {
        alert("Please Enter Password");
        document.signupForm.vpassword.focus();
        return false;
    }
    else if(document.signupForm.vcpassword.value == "")
    {
        alert("Please Confirm Password");
        document.signupForm.vcpassword.focus();
        return false;
    }
    else if(document.signupForm.vpassword.value != document.signupForm.vcpassword.value)
    {
        alert("Password Mismatch: Re-Enter");
        document.signupForm.vpassword.focus();
        return false;
    }

    else
    {
        return true;
    }
}