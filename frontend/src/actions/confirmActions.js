const enterConfirmPage = () => {
  return {
    type: "ENTER",
  };
};

const exitConfirmPage = () => {
  return {
    type: "EXIT",
  };
};

const confirmActions = { enterConfirmPage, exitConfirmPage };
export default confirmActions;
