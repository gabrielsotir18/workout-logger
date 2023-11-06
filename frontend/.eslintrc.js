module.exports = {
  'env': {
    'browser': true,
    'es2021': true,
    'node': true
  },
  'extends': [
    'eslint:recommended',
    'plugin:vue/vue3-recommended'
  ],
  'overrides': [
    {
      'env': {
        'node': true
      },
      'files': [
        '.eslintrc.{js,cjs}'
      ],
      'parserOptions': {
        'sourceType': 'script'
      }
    }
  ],
  'parserOptions': {
    'ecmaVersion': 'latest',
    'sourceType': 'module'
  },
  'plugins': [
    'vue'
  ],
  'rules': {
    'indent': [
      'error',
      2
    ],
    'linebreak-style': [
      'error',
      'unix'
    ],
    'quotes': [
      'error',
      'single'
    ],
    'semi': [
      'error',
      'never'
    ],
    'vue/multi-word-component-names': 0,
    'vue/require-prop-types': 0,
    'vue/require-default-prop': 0,
    'vue/max-attributes-per-line': [
      2,
      {
        singleline: 3,
        multiline: 3,
      },
    ],
    'vue/attribute-hyphenation': 'off',
    'max-len': ['error', { 'code': 80 }]
  }
}
