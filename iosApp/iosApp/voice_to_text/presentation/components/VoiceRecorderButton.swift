//
//  VoiceRecorderButton.swift
//  iosApp
//
//  Created by Betuel on 30.04.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct VoiceRecorderButton: View {
    let displayState: DisplayState
    let onClick: () -> Void
    
    var body: some View {
        Button(action: onClick) {
            ZStack {
                Circle()
                    .foregroundColor(.primaryColor)
                    .padding()
                icon
                    .foregroundColor(.onPrimary)
            }
        }
        .frame(maxWidth: 100.0, maxHeight: 100.0)
        .accessibilityIdentifier("Voice recorder button")
    }
    
    var icon: some View {
        switch displayState {
        case .speaking:
            return Image(systemName: "stop.fill")
        case .displayingResults:
            return Image(systemName: "checkmark")
        default:
            return Image(uiImage: UIImage(named: "mic")!)
        }
    }
}

#Preview {
    VoiceRecorderButton(displayState: .waitingToTalk, onClick: {})
}
