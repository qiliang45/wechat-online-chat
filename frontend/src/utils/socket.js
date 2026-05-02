import SockJS from 'sockjs-client'
import Stomp from 'stompjs'

let stompClient = null

export function connect(userId, onMessageReceived) {
  const socket = new SockJS('/ws')
  stompClient = Stomp.over(socket)
  
  stompClient.connect({}, () => {
    stompClient.subscribe(`/topic/user/${userId}`, (message) => {
      const data = JSON.parse(message.body)
      if (onMessageReceived) {
        onMessageReceived(data)
      }
    })
    
    stompClient.subscribe(`/topic/group/${userId}`, (message) => {
      const data = JSON.parse(message.body)
      if (onMessageReceived) {
        onMessageReceived(data)
      }
    })
  })
}

export function disconnect() {
  if (stompClient !== null) {
    stompClient.disconnect()
    stompClient = null
  }
}

export function sendMessage(message) {
  if (stompClient !== null) {
    stompClient.send('/app/chat/send', {}, JSON.stringify(message))
  }
}

export default {
  connect,
  disconnect,
  sendMessage
}