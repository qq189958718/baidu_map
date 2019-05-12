import React from 'react'
import { requireNativeComponent, View, ViewPropTypes, UIManager } from 'react-native'
import PropTypes from 'prop-types'

export default class MyButton extends React.PureComponent {
  static name = 'MyButton'
  static propTypes = {
    text: PropTypes.string,
    ...View.propTypes,
  }

  static defaultProps = {
    text: '按钮'
  }

  constructor(props) {
    super(props)
  }

  jsClick = event => {
    // console.log(event)
    alert(event.nativeEvent.msg)
  }

  render(): React.ReactNode {
    console.log(this.props)
    console.log(UIManager)
    const { style, ...otherProps } = this.props
    return <View>
      <RCTButton style={[{ width: 100, height: 50 }, style]} {...otherProps} onChange={e => this.jsClick(e)} />
    </View>
  }
}

const RCTButton = requireNativeComponent('MyButton', MyButton)
