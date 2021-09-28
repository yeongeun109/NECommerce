const currentUser = (state = {}, action) => {
  switch (action.type) {
    case "LOGIN":
      return {
        ...state,
        user: action.user,
        userName: action.userName,
        login: true,
      };
    case "LOGOUT":
      return { ...state, user: "", userName: "", login: false };
    default:
      return state;
  }
};

export default currentUser;
