const scrollDown = (state = {}, action) => {
  switch (action.type) {
    case "STICKY":
      return { ...state, isSticky: true };
    case "NORMAL":
      return { ...state, isSticky: false };
    default:
      return state;
  }
};

export default scrollDown;
