const scrollDown = () => {
  return {
    type: "STICKY",
  };
};

const scrollUp = () => {
  return {
    type: "NORMAL",
  };
};

const scrollActions = { scrollDown, scrollUp };
export default scrollActions;
