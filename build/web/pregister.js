function validate()
{

    if(document.partyForm.pname.value == "")
    {
        alert("Please Enter Party Name");
        document.partyForm.pname.focus();
        return false;
    }
    else if(document.partyForm.pmail.value == "")
    {
        alert("Please Enter Party Email");
        document.partyForm.pmail.focus();
        return false;
    }
    else if(document.partyForm.ppassword.value == "")
    {
        alert("Please Enter Password");
        document.partyForm.ppassword.focus();
        return false;
    }
    else if(document.partyForm.pcpassword.value == "")
    {
        alert("Please Confirm Password");
        document.partyForm.pcpassword.focus();
        return false;
    }
    else if(document.partyForm.ppassword.value != document.partyForm.pcpassword.value)
    {
        alert("Password Mismatch");
        document.partyForm.ppassword.focus();
        return false;
    }
    else
    {
        return true;
    }
}