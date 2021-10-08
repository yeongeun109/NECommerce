const isEnter = (state = {}, action) => {
  switch (action.type) {
    case "ENTER":
      return { ...state, isConfirm: true };
    case "EXIT":
      return { ...state, isConfirm: false };
    default:
      return state;
  }
};

export default isEnter;
