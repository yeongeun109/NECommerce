const loginUser = (user, userName) => {
  console.log(user, userName);
  return {
    type: "LOGIN",
    user,
    userName,
  };
};

const logoutUser = () => {
  return {
    type: "LOGOUT",
  };
};

const userActions = { loginUser, logoutUser };
export default userActions;
