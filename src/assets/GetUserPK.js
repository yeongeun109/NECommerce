import jwtDecode from "jwt-decode";



const GetUserPK = () => {
    const token = window.localStorage.getItem("token")
    const decoded = jwtDecode(token)
    console.log(decoded.uid)
    return decoded.uid
  }

export default GetUserPK