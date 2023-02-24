module.exports = {
  name: 'continuous-integration',
  exposes: {
    './Routes':
      'apps/continuous-integration/src/app/remote-entry/entry.routes.ts',
  },
};
