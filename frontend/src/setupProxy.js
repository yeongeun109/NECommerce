const { createProxyMiddleware } = require("http-proxy-middleware");
module.exports = function (app) {
  app.use(
    "/api",
    createProxyMiddleware({
      target: "https://j5a407.p.ssafy.io",
      changeOrigin: true,
    })
  );
  
};
