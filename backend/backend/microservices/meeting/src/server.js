const app = require('./app');
const Eureka = require('eureka-js-client').Eureka;

const PORT = process.env.PORT || 8083;

app.listen(PORT, () => {
  console.log(`meeting microservice running on http://localhost:${PORT}`);
  console.log(`Swagger UI: http://localhost:${PORT}/swagger-ui`);

  // ---- Eureka registration ----
  const eurekaClient = new Eureka({
    instance: {
      app: 'MEETING',
      instanceId: `meeting-${PORT}`,
      hostName: 'localhost',
      ipAddr: '127.0.0.1',
      port: {
        '$': PORT,
        '@enabled': true,
      },
      vipAddress: 'meeting',
      dataCenterInfo: {
        '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
        name: 'MyOwn',
      },
      statusPageUrl: `http://localhost:${PORT}/api/meetings/hello`,
      healthCheckUrl: `http://localhost:${PORT}/api/meetings/hello`,
      homePageUrl: `http://localhost:${PORT}/`,
    },
    eureka: {
      host: 'localhost',
      port: 8761,
      servicePath: '/eureka/apps/',
    },
  });

  eurekaClient.start((error) => {
    if (error) {
      console.error('Eureka registration failed:', error);
    } else {
      console.log('Registered with Eureka');
    }
  });

  // Graceful shutdown
  const shutdown = () => {
    eurekaClient.stop(() => {
      console.log('De-registered from Eureka');
      process.exit(0);
    });
  };
  process.on('SIGINT', shutdown);
  process.on('SIGTERM', shutdown);
});
