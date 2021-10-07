import jwtDecode from "jwt-decode";

const GetUserPK = () => {
    const token = window.localStorage.getItem("token")
    const decoded = jwtDecode(token)
    return decoded.uid
  }

export default GetUserPK