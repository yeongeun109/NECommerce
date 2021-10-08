import React from "react";
import { Route, Redirect } from "react-router-dom";
import { useSelector } from "react-redux";

const PublicRouter = ({ component: Component, ...rest }) => {
  const currentUser = useSelector((state) => state.currentUser);
  return (
    <Route
      {...rest}
      render={(props) =>
        (currentUser.login) ? (
          <Redirect to="/main" />
        ) : (
          <Component {...props} />
        )
      }
    />
  );
};

export default PublicRouter;
