function validate()
{
    
    if(document.loginForm.vid.value=="")
    {
        alert("Please Enter your identity card number");
        document.loginForm.vid.focus();
        return false;
    }
    else if(document.loginForm.vpassword.value=="")
    {
        alert("Please Enter your Password");
        document.loginForm.vpassword.focus();
        return false;
    }
    else
    {
        return true;
    }
    
}
function validate2()
{
    
    if(document.adminForm.pid.value=="")
    {
        alert("Please Enter Party ID");
        document.adminForm.pid.focus();
        return false;
    }
    else if(document.adminForm.ppassword.value=="")
    {
        alert("Please Enter your Password");
        document.adminForm.ppassword.focus();
        return false;
    }
    else
    {
        return true;
    }
    
}